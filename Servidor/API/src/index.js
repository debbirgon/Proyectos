import express from 'express'
import config from './config'
import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'

//Modelos:
import models from '../models'
import Perfil from models.Perfil

const BASE_API_PATH = "/api/v1";

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

		app.get('/perfiles', (req, res, next) => {
			console.log(Date() + " - GET /perfiles")
			
			/*
			sequelize.Sequelize.Perfil.findOne({ where: {nombre:  'Cuidador'}}).then(data => {
				console.log(data.data)
				res.send(data.data)
			})
			console.log("con (): "+Perfil.fullName())
			console.log("sin (): "+Perfil.fullName)
			res.send(Perfil.fullName())
			*/
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


		_server = app.listen(process.env.PORT, () => {
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