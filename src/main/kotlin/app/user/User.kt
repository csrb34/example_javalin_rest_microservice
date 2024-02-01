package app.user

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

data class User @JsonCreator constructor (
    @JsonProperty("name") val name: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("id") val id: Int
)
