import sequelize from '../common/mysql.js'
import Sequelize from 'sequelize'
import Persona from './persona'
import Cuidador from './cuidador'

const Dependiente = sequelize.define(
	'DEPENDIENTES',
	{
		ID: {
			type: Sequelize.BIGINT(11),
			autoIncrement: true,
			field: 'ID',
			allowNull: false,
			primaryKey: true
		},
		ALIAS: {
			type: Sequelize.STRING(45),
			allowNull: false,
			field: 'ALIAS'
		},
		ID_PERSONA: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Persona,
				key: 'ID'
			},
			field: 'ID_PERSONA'
		},
		ID_CUIDADOR: {
			type: Sequelize.BIGINT(11),
			references: {
				model: Cuidador,
				key: 'ID'
			},
			field: 'ID_CUIDADOR'
		}
	},
	{
		timestamps: false,
		freezeTableName: true //Desactiva la modificación de los campos de la base de datos
	}
)

module.exports = Dependiente