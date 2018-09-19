import dosis from './routes/dosis'
import perfiles from './routes/perfiles'
import tratamientos from './routes/tratamientos'
import medicamentos from './routes/medicamentos'
import cargas from './routes/cargas'

const BASE_API_PATH = "/api/v1";

export default app => {
    app.use(BASE_API_PATH+'/dosis', dosis)
    app.use(BASE_API_PATH+'/perfiles', perfiles)
    app.use(BASE_API_PATH+'/tratamientos', tratamientos)
    app.use(BASE_API_PATH+'/medicamentos', medicamentos)
    app.use(BASE_API_PATH+'/cargas', cargas)
}