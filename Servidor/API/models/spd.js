import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'

const Spd = sequelize.define(
	'Spds',
	{
		idSpds: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'idSpds',
			allowNull: false,
			primaryKey: true
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Spd