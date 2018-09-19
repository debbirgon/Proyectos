import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import Dependiente from './dependiente'
import Spd from './spd'

const Tratamiento = sequelize.define(
	'TRATAMIENTOS',
	{
		ID: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'ID',
			allowNull: false,
			primaryKey: true
		},
		ID_DEPENDIENTE: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Dependiente,
				key: 'ID'
			},
			field: 'ID_DEPENDIENTE'
		},
		ID_SPD: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Spd,
				key: 'ID'
			},
			field: 'ID_SPD'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Tratamiento