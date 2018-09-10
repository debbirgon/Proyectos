import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'

const Medicamento = sequelize.define(
	'Medicamentos',
	{
		idMedicamento: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'idMedicamento',
			allowNull: false,
			primaryKey: true
		},
		nombre: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'nombre'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Medicamento