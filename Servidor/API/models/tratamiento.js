import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import Dependiente from './dependiente'
import Spd from './spd'

const Tratamiento = sequelize.define(
	'Tratamientos',
	{
		idTratamientos: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'idTratamientos',
			allowNull: false,
			primaryKey: true
		},
		dependiente: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Dependiente,
				key: 'idDependientes'
			},
			field: 'dependiente'
		},
		spd: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Spd,
				key: 'idSpds'
			},
			field: 'spd'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Tratamiento