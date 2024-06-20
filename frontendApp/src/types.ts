export interface User {
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

export interface Author {
    id: number;
    username: string;

}

export interface Post {
    id: number;
    content: string;
    author: Author;
}

export interface Topic {
    id: number;
    title: string;
    content: string;
    author: Author
    posts: Post[];
}
