import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import Persona from './persona'

const Cuidador = sequelize.define(
	'Cuidadores',
	{
		idCuidadores: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'idCuidadores',
			allowNull: false,
			primaryKey: true
		},
		persona: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Persona,
				key: 'idPersonas'
			},
			field: 'persona'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Cuidador