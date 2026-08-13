package gaur.himanshu.workmanageryt.domain.model


data class Quote(
    val author: String,
    val id: Int,
    val quote: String,
    val workType: String,
    val time:Long=System.currentTimeMillis()
)