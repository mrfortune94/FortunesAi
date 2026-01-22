package com.fortunateworld.grokxxx.data.api

import retrofit2.http.*

interface GrokApi {
    @POST("v1/chat/completions")
    suspend fun chatCompletion(@Body request: ChatRequest): ChatResponse

    @POST("v1/images/generations")
    suspend fun generateImage(@Body request: ImageRequest): ImageResponse

    @GET("v1/models")
    suspend fun getModels(): ModelsResponse
}

data class ChatRequest(val model: String, val messages: List<Message>, val temperature: Double = 0.9, val max_tokens: Int = 2000)
data class Message(val role: String, val content: String)
data class ChatResponse(val choices: List<Choice>)
data class Choice(val message: Message)

data class ImageRequest(val model: String, val prompt: String, val n: Int = 1, val size: String = "1024x1024")
data class ImageResponse(val data: List<ImageData>)
data class ImageData(val url: String)

data class ModelsResponse(val data: List<ModelInfo>)
data class ModelInfo(val id: String)
