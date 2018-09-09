/*
  GSM.h
  2013 Copyright (c) Seeed Technology Inc.  All right reserved.

  Author:lawliet.zou@gmail.com
  2013-11-14

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2.1 of the License, or (at your option) any later version.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public
  License along with this library; if not, write to the Free Software
  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*/

#ifndef __GSM_H__
#define __GSM_H__

#include <stdio.h>
#include "mbed.h"

#define DEFAULT_TIMEOUT         5
#define SMS_MAX_LENGTH          16


enum GSM_MESSAGE {
    MESSAGE_RING = 0,
    MESSAGE_SMS  = 1,
    MESSAGE_ERROR
};


/** GSM class.
 *  Used for mobile communication. attention that GSM module communicate with MCU in serial protocol
 */
class GSM
{
public:
    /** Create GSM instance
     *  @param tx  uart transmit pin to communicate with GSM module
     *  @param rx  uart receive pin to communicate with GSM module
     *  @param baudRate baud rate of uart communication
     *  @param number default phone number during mobile communication
     */
    GSM(PinName tx, PinName rx, int baudRate,char *number) : gprsSerial(tx, rx) {
        //gprsSerial.baud(baudRate);
        phoneNumber = number;
    };
    
    int powerCheck(void);
    /** init GSM module including SIM card check & signal strength & network check
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int init(void);

    /** Check SIM card' Status
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int checkSIMStatus(void);

    /** Check signal strength
     *  @returns
     *      signal strength in number(ex 3,4,5,6,7,8...) on success,
     *      -1 on error
     */
    int checkSignalStrength(void);

    /** Set SMS format and processing mode
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int settingSMS(void);

    /** Send text SMS
     *  @param  *number    phone number which SMS will be send to
     *  @param  *data   message that will be send to
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int sendSMS(char *number, char *data);

    /** Read SMS by index
     *  @param  *message   buffer used to get SMS message
     *  @param  index    which SMS message to read
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int readSMS(char *message, int index);

    /** Delete SMS message on SIM card
     *  @param  *index    the index number which SMS message will be delete
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int deleteSMS(int index);

    /** Read SMS when coming a message,it will be store in messageBuffer.
     *  @param message  buffer used to get SMS message
     */
    int getSMS(char* message);

    /** Call someone
     *  @param  *number    the phone number which you want to call
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int callUp(char *number);

    /** Auto answer if coming a call
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int answer(void);

    /** A loop to wait for some event. if a call comes in, it will auto answer it and if a SMS message comes in, it will read the message
     *  @param  *check    whether to check phone number when get event
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int loopHandle(void);

    /** GSM network init
     *  @param *apn Access  Point Name to connect network
     *  @param *userName    general is empty
     *  @param *passWord    general is empty
     */

    int networkInit(char* apn, char* userName = NULL, char* passWord = NULL);
    /** Build TCP connect
     *  @param  *ip    ip address which will connect to
     *  @param  *port   TCP server' port number
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int connectTCP(char *ip, char *port);

    /** Send data to TCP server
     *  @param  *data    data that will be send to TCP server
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int sendTCPData(char *data);

    /** Close TCP connection
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int closeTCP(void);

    /** Close TCP service
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int shutTCP(void);
    
     int sendCmdAndWaitForResp(char *cmd, char *resp, int timeout);

    Serial gprsSerial;
    //USBSerial pc;

private:
    /** Read from GSM module and save to buffer array
     *  @param  *buffer buffer array to save what read from GSM module
     *  @param  *count  the maximal bytes number read from GSM module
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int readBuffer(char *buffer,int count);

    /** Send AT command to GSM module
     *  @param  *cmd command array which will be send to GSM module
     */
    void sendCmd(char *cmd);

    /** Check GSM module response before timeout
     *  @param  *resp   correct response which GSM module will return
     *  @param  *timeout    waiting seconds till timeout
     *  @returns
     *      0 on success,
     *      -1 on error
     */
    int waitForResp(char *resp, int timeout);

    /** Send AT command to GSM module and wait for correct response
     *  @param  *cmd    AT command which will be send to GSM module
     *  @param  *resp   correct response which GSM module will return
     *  @param  *timeout    waiting seconds till timeout
     *  @returns
     *      0 on success,
     *      -1 on error
     */
   

    Timer timeCnt;
    char *phoneNumber;
    char messageBuffer[SMS_MAX_LENGTH];
};

#endif

