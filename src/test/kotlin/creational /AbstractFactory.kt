package `creational `

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

interface DataSource

class DatabaseDataSource : DataSource

class NetworkDatSource : DataSource

abstract class DatasourceFactory {
    abstract fun makeDataSource(): DataSource

    companion object {
        inline fun <reified T : DataSource> createFactory(): DatasourceFactory =
            when (T::class) {
                DatabaseDataSource::class -> DatabaseFactory()
                NetworkDatSource::class -> NetworkFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class NetworkFactory : DatasourceFactory() {
    override fun makeDataSource(): DataSource = NetworkDatSource()

}

class DatabaseFactory : DatasourceFactory() {
    override fun makeDataSource(): DataSource = DatabaseDataSource()

}


class AbstractFactoryTest {
    @Test
    fun aftest() {
        val datasourceFactory = DatasourceFactory.createFactory<DatabaseDataSource>()
        val dataSource = datasourceFactory.makeDataSource()
        println("Created datasource: $dataSource")

        Assertions.assertThat(dataSource).isInstanceOf(DatabaseDataSource::class.java)
    }
}