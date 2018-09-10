import express from 'express'
import config from './config'
import sequelize from '../common/mysql.js'

let _server

//Probamos la conexión con la base de datos
sequelize.authenticate().then(err => {
	if (err) {
	   console.log('Hay un error con la conexión de la base de datos');
	} else {
	   console.log('La conexión con la base de datos ha tenido éxito');
	}
});

const server = {
	start(){
		const app = express()

		config(app)

		//Rutas:

		app.get('/', (req, res, next) => {
			res
			.status(200)
			.json({ data: "prueba metodo get"})

		})

		app.post('/', (req, res, next) => {
			res
			.status(200)
			.json({ data: "prueba metodo post"})
		})

		app.put('/', (req, res, next) => {
			res
			.status(200)
			.json({ data: "prueba metodo put"})
		})

		app.delete('/', (req, res, next) => {
			res
			.status(200)
			.json({ data: "prueba metodo delete"})
		})


		_server = app.listen('9000', () => {
			if(process.env.NODE_ENV !== "test"){
				console.log("Servidor corriendo en http://localhost:9000")
			}
		})
	},
	close(){
		_server.close();
	}
}

export default server

if(!module.parent){
	server.start();
}