<script setup lang="ts">
import type {Topic} from "~/types";

const route = useRoute()
const auth = useAuthStore()
const topicId = parseInt(<string>route.params.id)

const {data: currentTopic, refresh: refreshCurrentTopic} = await useFetchApi<Topic>(`/api/v1/topics/${topicId}`, {
  headers: {Authorization: 'Bearer ' + auth.token},
})
</script>

<template>
  <single-topic :id="currentTopic?.id || 0"
                :title="currentTopic?.title || 'Problem z wczytaniem tematu'"
                :content="currentTopic?.content || ''"
                :author="currentTopic?.author || {username: '', id: 0} "
                :posts="currentTopic?.posts || []"
                :refresh="refreshCurrentTopic"
  />

  <single-post v-for="post in currentTopic?.posts" :key="post.id"
               :id="post.id"
               :content="post.content"
               :author="post.author"
               :refresh="refreshCurrentTopic"
  />

  <post-creation-form :topicId="currentTopic?.id || 0" :refresh="refreshCurrentTopic" class="mx-1/6 mt-4rem"/>
</template>
