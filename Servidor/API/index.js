import express from 'express'
import config from './config'
import sequelize from './common/mysql'
import Sequelize from 'sequelize'

//Modelos:
//import models from 'models'
import Perfil from './models/perfil'
import Spd from './models/spd'
import Tratamiento from './models/tratamiento'
import Dosis from './models/dosis'

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

		app.get(BASE_API_PATH + '/perfiles', (req, res, next) => {
			console.log(Date() + " - GET /perfiles")
			Perfil.findAll().then(projects => {
				// projects will be an array of all Project instances
				/* delete projects.idperfiles
				res.send(projects.map(c) => {})
				console.log("DATOS: "+projects) */
				res.send(projects.map((c) => {
					//var c2 = JSON.parse(c)
					delete c.idperfiles; //Quitamos el campo id
					return c;
				}));
			  })

			/* var perfil1 = Perfil.build({
				nombre:'Nuevo correcto',
				descripcion: 'asi es'
			});
			//Inserting Data into database
			perfil1.save().then(res => {
				console.log('Data successfully inserted');
			}).catch(error => {
				console.log('Error in Inserting Record: '+err);
			}) */

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

		//Dosis por SPD:

		/*
			0) Creamos el array dosisRet para devolver los datos
			1) Busco los tratamientos asociados al SPD.
			2) Una vez encontrados, recorro todos los tratamientos y busco las Dosis asociadas
			3) Una vez encontrada las Dosis, las introduzco en el Array creado al principio y lo devolvemos tras de todo.
		*/
		app.get(BASE_API_PATH + '/dosis/:spd', (req, res, next) => {
			var codeSpd = parseInt(req.params.spd)
			var dosisRet = {}
			console.log(Date() + " - GET /dosis/" + codeSpd)
			Tratamiento.findAll({ where: { spd: codeSpd } }).then(projects => {
				projects.forEach(e => {
					Dosis.finAll({where: {tratamiento: e.idtratamientos}}).then(p => {
						p.forEach(dosis => {
							dosisRet.push(dosis)
						})
					})
				})
		  }).then(
			  res.send(dosisRet)
		  )
		  
		})

		app.post(BASE_API_PATH + '/dosis/:spd', (req, res, next) => {
			res
			.status(405)
		})

		app.put(BASE_API_PATH + '/dosis/:spd', (req, res, next) => {
			res
			.status(405)
		})

		app.delete(BASE_API_PATH + '/dosis/:spd', (req, res, next) => {
			res
			.status(405)
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