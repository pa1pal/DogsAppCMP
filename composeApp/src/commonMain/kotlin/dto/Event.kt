package dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Event(
    @SerialName("branch_key")
    val branchKey: String? = null,
    @SerialName("custom_data")
    val customData: CustomData? = null,
    @SerialName("customer_event_alias")
    val customerEventAlias: String? = null,
    @SerialName("metadata")
    val metadata: Metadata? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("user_data")
    val userData: UserData?
)

@Serializable
data class CustomData(
    @SerialName("breed")
    val breed: String? = null
)

@Serializable
class Metadata

@Serializable
data class UserData(
    @SerialName("aaid")
    val aaid: String? = null,
    @SerialName("advertising_ids")
    val advertisingIds: AdvertisingIds? = null,
    @SerialName("android_id")
    val androidId: String? = null,
    @SerialName("app_version")
    val appVersion: String? = null,
    @SerialName("brand")
    val brand: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("developer_identity")
    val developerIdentity: String? = null,
    @SerialName("environment")
    val environment: String? = null,
    @SerialName("ip")
    val ip: String? = null,
    @SerialName("language")
    val language: String? = null,
    @SerialName("limit_ad_tracking")
    val limitAdTracking: Boolean? = null,
    @SerialName("local_ip")
    val localIp: String? = null,
    @SerialName("model")
    val model: String? = null,
    @SerialName("os")
    val os: String? = null,
    @SerialName("os_version")
    val osVersion: Int? = null,
    @SerialName("screen_dpi")
    val screenDpi: Int? = null,
    @SerialName("screen_height")
    val screenHeight: Int? = null,
    @SerialName("screen_width")
    val screenWidth: Int? = null
)

@Serializable
data class AdvertisingIds(
    @SerialName("oaid")
    val oaid: String? = null
)