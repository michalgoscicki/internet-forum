export interface User {
    id: number;
    username: string;
    email: string;
    password: string;
    name: string;
    surname: string;
    roles: {
        id: number;
        name: string;
    }[];
}

export interface Follower {
    id: number;
    username: string;
    email: string;
}

export interface NewUser {
    id: number;
    username: string;
    email: string;
    password: string;
    name: string;
    surname: string;
}

export interface NewTopic {
    title: string;
    content: string;
}

export interface NewPost {
    id: number;
    content: string;
}

export interface Author {
    id: number;
    username: string;

}

export interface Post {
    id: number;
    content: string;
    author: Author;
    refresh: Function;
}

export interface Topic {
    id: number;
    title: string;
    content: string;
    author: Author
    posts: Post[];
    refresh: Function;
}
