import express from 'express';

import config from './config'

let _server

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