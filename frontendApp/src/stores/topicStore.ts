import type {NewPost, NewTopic, Topic} from "~/types";

export const useTopicStore = defineStore("topicStore", () => {
        const auth = useAuthStore()


        const {data: topicList, refresh: refreshTopicList} = useFetchApi<Topic[]>("/api/v1/topics", {
            headers: {Authorization: 'Bearer ' + auth.token},

        })

        const createTopic = async (topic: NewTopic) => {
            console.log(topic)
            await $fetchApi("/api/v1/topics", {
                method: "POST",
                body: {
                    title: topic.title,
                    content: topic.content,
                },
                headers: {Authorization: 'Bearer ' + auth.token},
            }).catch((error) => console.error(error));
        };

        const createPost = async (post: NewPost) => {
            await $fetchApi(`/api/v1/topics/${post.id}/post`, {
                method: "POST",
                body: {
                    content: post.content,
                },
                headers: {Authorization: 'Bearer ' + auth.token},
            }).catch((error) => console.error(error));
        };

        const deleteTopic = async (topicId: number) => {
            await $fetchApi(`/api/v1/topics/${topicId}`, {
                method: "DELETE",
                headers: {Authorization: 'Bearer ' + auth.token},
            }).catch((error) => console.error(error));
        }

        const deletePost = async (topicId: number, postId: number) => {
            await $fetchApi(`/api/v1/topics/${topicId}/post/${postId}`, {
                method: "DELETE",
                headers: {Authorization: 'Bearer ' + auth.token},
            }).catch((error) => console.error(error));
        }

        const editTopic = async (topicId: number, title: string, content: string) => {
            await $fetchApi(`/api/v1/topics/${topicId}`, {
                method: "PUT",
                body: {
                    title: title,
                    content: content,
                },
                headers: {Authorization: 'Bearer ' + auth.token},
            }).catch((error) => console.error(error));
        }

        const editPost = async (topicId: number, postId: number, content: string) => {
            await $fetchApi(`/api/v1/topics/${topicId}/post/${postId}`, {
                method: "PUT",
                body: {
                    content: content,
                },
                headers: {Authorization: 'Bearer ' + auth.token},
            }).catch((error) => console.error(error));
        };

        return {
            createTopic,
            deleteTopic,
            topicList,
            refreshTopicList,
            createPost,
            deletePost,
            editPost,
            editTopic,
        };
    })
;

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useTopicStore, import.meta.hot));
}
