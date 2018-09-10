import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import User from './spd'
import Medicamento from './medicamento'

const Carga = sequelize.define(
	'Cargas',
	{
		idCargas: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'idCargas',
			allowNull: false,
			primaryKey: true
		},
		spd: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Spd,
				key: 'idSpds'
			},
			field: 'spd'
		},
		medicamento: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Medicamento,
				key: 'idMedicamento'
			},
			field: 'medicamento'
		},
		cantidad: {
			type: Sequelize.BIGINT(11),
			allowNull: false,
			field: 'cantidad'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Carga