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
		router.get('/:spd', (req, res, next) => {
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

		router.post('/:spd', (req, res, next) => {
			console.log(Date() + " - POST /dosis/" + req.params.spd)
			res
			.sendStatus(405)
		})

		router.put('/:spd', (req, res, next) => {
			console.log(Date() + " - PUT /dosis/" + req.params.spd)
			res
			.sendStatus(405)
		})

		router.delete('/:spd', (req, res, next) => {
			console.log(Date() + " - DELETE /dosis/" + req.params.spd)
			res
			.sendStatus(405)
		})

export default router