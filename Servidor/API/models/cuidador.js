import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import Persona from './persona'

const Cuidador = sequelize.define(
	'CUIDADORES',
	{
		ID: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'ID',
			allowNull: false,
			primaryKey: true
		},
		ID_PERSONA: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Persona,
				key: 'ID'
			},
			field: 'ID_PERSONA'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Cuidador