import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'

const Medicamento = sequelize.define(
	'MEDICAMENTOS',
	{
		ID: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'ID',
			allowNull: false,
			primaryKey: true
		},
		NOMBRE: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'NOMBRE'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Medicamento