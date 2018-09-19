import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import Spd from './spd'
import Medicamento from './medicamento'

const Carga = sequelize.define(
	'CARGAS',
	{
		ID: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'ID',
			allowNull: false,
			primaryKey: true
		},
		ID_SPD: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Spd,
				key: 'ID'
			},
			field: 'ID_SPD'
		},
		ID_MEDICAMENTO: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Medicamento,
				key: 'ID'
			},
			field: 'ID_MEDICAMENTO'
		},
		CANTIDAD: {
			type: Sequelize.BIGINT(11),
			allowNull: false,
			field: 'CANTIDAD'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Carga