import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import Medicamento from './medicamento'
import Tratamiento from './tratamiento'

const Dosis = sequelize.define(
	'DOSIS',
	{
		ID: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'ID',
			allowNull: false,
			primaryKey: true
		},
		HORA_INICIO: {
			type: Sequelize.TIME,
			allowNull: false,
			field: 'HORA_INICIO'
		},
		VECES_POR_DIA: {
			type: Sequelize.BIGINT(11),
			allowNull: false,
			field: 'VECES_POR_DIA'
		},
		ID_MEDICAMENTO: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Medicamento,
				key: 'ID'
			},
			field: 'ID_MEDICAMENTO'
		},
		ID_TRATAMIENTO: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Tratamiento,
				key: 'ID'
			},
			field: 'ID_TRATAMIENTO'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Dosis