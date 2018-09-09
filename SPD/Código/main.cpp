#include "mbed.h"
#include <string>
#include "GSM.h"

int level_count = 0;
int giro;
 
DigitalOut Led_A_1(PB_8);
DigitalOut Led_A_2(PC_9);
DigitalOut Led_R(PC_8);

InterruptIn button_drop(PC_5);// drops a pill from the selected level
InterruptIn button_level(PB_14);// changes the level
InterruptIn button_portion(PB_15);// rotates the level 1 portion
InterruptIn button_message(PC_13);


Serial pc(USBTX, USBRX);

void stepper(PinName, PinName, PinName, PinName, int, int,int);
  // blue - purple - grey - white
void drop();
void level();
void portion();
void message();


//Main program ---------------------------------------------------------

int main() {
   button_drop.rise(&drop);
   button_level.rise(&level);
   button_portion.rise(&portion);
   button_message.rise(&message);
   Led_A_1 = 1;
  
    while(1) {
       
    }
}

// Functions----------------------------------------------------------------

void drop(){
    int i = 0;
    switch(level_count){
        case 0:
            do{
                stepper(D6,D7,D8,D9,1,1,0);
                i++;
            }while(i!=140);
            break;
        case 1:
            do{
                stepper(D10,D11,D12,D13,0,1,0);
                i++;
            }while(i!=140);
            break;
    }
}
    
void level(){
    if(level_count == 0)level_count++; else level_count--;  
    switch(level_count){
        case 0: 
            Led_A_1 = 1;
            Led_A_2 = 0;
            break;
        case 1:
            Led_A_2 = 1;
            Led_A_1 = 0;
            break;
    }
    
}

void portion(){
    int i = 0;
    do{
        stepper(D2,D3,D4,D5,1,1,0);
        i++;
    }while(i!=140);
}

void message(){
    int i;
    Led_R =1;
    GSM sim(PA_9,PA_10,9600,"608864024");
    i=sim.sendCmdAndWaitForResp("AT+cpin=7427\r\n","OK",10);
    wait(10);
    i = sim.settingSMS();
    pc.printf("\n %d \n",i);
    i = sim.sendSMS("+34686489602","prueba");
    i = sim.callUp("+34686489602");
    pc.printf("\n %d \n",i);
    Led_R = 0;
}
   

void stepper(PinName blue, PinName purple, PinName grey, PinName white, int dir, int speed, int step){
    BusOut motor_out(blue, purple, grey, white); 
    if(dir == 0)step=7;   
    while(step >= 0 && step <= 7){
        switch(step)
        { 
            case 0: motor_out = 0x1; break;  // 0001
            case 1: motor_out = 0x3; break;  // 0011
            case 2: motor_out = 0x2; break;  // 0010   
            case 3: motor_out = 0x6; break;  // 0110
            case 4: motor_out = 0x4; break;  // 0100
            case 5: motor_out = 0xC; break;  // 1100
            case 6: motor_out = 0x8; break;  // 1000
            case 7: motor_out = 0x9; break;  // 1001
            
            default: motor_out = 0x0; break; // 0000
        }
        if(dir) step++; else step--;
        wait_ms(speed);
    } 
      // speed
}
    
    
    
    