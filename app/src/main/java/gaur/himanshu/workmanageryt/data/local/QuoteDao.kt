package gaur.himanshu.workmanageryt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gaur.himanshu.workmanageryt.domain.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserQuote(quote: Quote)

    @Query("select * from Quote Order by time desc")
    fun getAlllQuotes(): Flow<List<Quote>>

}