import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import Medicamento from './medicamento'
import Tratamiento from './tratamiento'

const Dosis = sequelize.define(
	'Dosis',
	{
		idDosis: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'idDosis',
			allowNull: false,
			primaryKey: true
		},
		hora_inicio: {
			type: Sequelize.TIME,
			allowNull: false,
			field: 'hora_inicio'
		},
		veces_dia: {
			type: Sequelize.BIGINT(11),
			allowNull: false,
			field: 'veces_dia'
		},
		medicamento: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Medicamento,
				key: 'idMedicamentos'
			},
			field: 'medicamento'
		},
		tratamiento: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Tratamiento,
				key: 'idTratamientos'
			},
			field: 'tratamiento'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Dosis