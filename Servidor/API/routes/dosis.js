import express from 'express'

//Modelos:
import Spd from '../models/spd'
import Tratamiento from '../models/tratamiento'
import Dosis from '../models/dosis'

const router = express.Router()

//Dosis por SPD:

		/*
			0) Creamos el array dosisRet para devolver los datos
			1) Busco los tratamientos asociados al SPD.
			2) Una vez encontrados, recorro todos los tratamientos y busco las Dosis asociadas
			3) Una vez encontrada las Dosis, las introduzco en el Array creado al principio y lo devolvemos tras de todo.
		*/
		router.get('/spd/:spd', (req, res, next) => {
			var codeSpd = parseInt(req.params.spd)
			console.log(Date() + " - GET /dosis/" + codeSpd)
			Tratamiento.findAll({ where: { ID_SPD: codeSpd } }).then(projects => {
				projects.forEach(e => {
					Dosis.findAll({where: {ID_TRATAMIENTO: e.ID}}).then(p => {
						res.send(p)
					})
				})
		  })
		})

		router.post('/spd/:spd', (req, res, next) => {
			console.log(Date() + " - POST /dosis/" + req.params.spd)
			res
			.sendStatus(405)
		})

		router.put('/spd/:spd', (req, res, next) => {
			console.log(Date() + " - PUT /dosis/" + req.params.spd)
			res
			.sendStatus(405)
		})

		router.delete('/spd/:spd', (req, res, next) => {
			console.log(Date() + " - DELETE /dosis/" + req.params.spd)
			res
			.sendStatus(405)
		})

//Dosis por DEPENDIENTE:

		/*
			0) Creamos el array dosisRet para devolver los datos
			1) Busco los tratamientos asociados al SPD.
			2) Una vez encontrados, recorro todos los tratamientos y busco las Dosis asociadas
			3) Una vez encontrada las Dosis, las introduzco en el Array creado al principio y lo devolvemos tras de todo.
		*/
		router.get('/dependiente/:id_dependiente', (req, res, next) => {
			var id_dependiente_param = parseInt(req.params.id_dependiente)
			console.log(Date() + " - GET /dosis/dependiente/" + id_dependiente_param)
			Tratamiento.findAll({ where: { ID_DEPENDIENTE: id_dependiente_param } }).then(projects => {
				projects.forEach(e => {
					Dosis.findAll({where: {ID_TRATAMIENTO: e.ID}}).then(p => {
						res.send(p)
					})
				})
		  })
		})

		router.post('/dependiente/:id_dependiente', (req, res, next) => {
			console.log(Date() + " - POST /dosis/dependiente/" + req.params.id_dependiente)
			res
			.sendStatus(405)
		})

		router.put('/dependiente/:id_dependiente', (req, res, next) => {
			console.log(Date() + " - PUT /dosis/dependiente/" + req.params.id_dependiente)
			res
			.sendStatus(405)
		})

		router.delete('/dependiente/:id_dependiente', (req, res, next) => {
			console.log(Date() + " - DELETE /dosis/dependiente/" + req.params.id_dependiente)
			res
			.sendStatus(405)
		})

export default router