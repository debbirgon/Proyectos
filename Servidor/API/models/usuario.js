import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import Perfil from './perfil'
import Cuidador from './cuidador'

const Usuario = sequelize.define(
	'USUARIOS',
	{
		ID: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'ID',
			allowNull: false,
			primaryKey: true
		},
		USERNAME: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'USERNAME'
		},
		PASSWORD: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'PASSWORD'
		},
		ID_CUIDADOR: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Cuidador,
				key: 'ID'
			},
			field: 'ID_CUIDADOR'
		},
		ID_PERFIL: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Perfil,
				key: 'ID'
			},
			field: 'ID_PERFIL'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Usuario