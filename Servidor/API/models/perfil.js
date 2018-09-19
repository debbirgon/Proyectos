import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'

const Perfil = sequelize.define(
	'PERFILES',
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
		},
		DESCRIPCION: {
			type: Sequelize.STRING(255),
			allowNull: true,
			field: 'DESCRIPCION'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Perfil