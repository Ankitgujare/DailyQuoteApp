package gaur.himanshu.workmanageryt.data.mappers

import gaur.himanshu.workmanageryt.data.model.QuotesDto
import gaur.himanshu.workmanageryt.domain.model.Quote


fun QuotesDto.toDomain(worktype:String): Quote{
    return Quote(author,id,quote,worktype)
}