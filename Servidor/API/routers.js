import dosis from './routes/dosis'
import perfiles from './routes/perfiles'

const BASE_API_PATH = "/api/v1";

export default app => {
    app.use(BASE_API_PATH+'/dosis', dosis)
    app.use(BASE_API_PATH+'/perfiles', perfiles)
}