import express from 'express'

//Modelos:
import Perfil from '../models/perfil'

const router = express.Router()

router.get('/', (req, res, next) => {
    console.log(Date() + " - GET /perfiles")
    Perfil.findAll().then(projects => {
        res.send(projects.map((c) => {
            delete c.idperfiles; //Quitamos el campo id
            return c;
        }));
      })
})

router.post('/', (req, res, next) => {
    console.log(Date() + " - POST /perfiles")
    res
    .sendStatus(405)
})

router.put('/', (req, res, next) => {
    console.log(Date() + " - PUT /perfiles")
    res
    .sendStatus(405)
})

router.delete('/', (req, res, next) => {
    console.log(Date() + " - DELETE /perfiles")
    res
    .sendStatus(405)
})

export default router