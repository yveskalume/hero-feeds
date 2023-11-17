# hero-feeds (Work in progress)

This small project serves as a playground for me to pratice some KMM stuff. The goal is to save
creators and urls to their different content platforms and fetch their posts inside the app.

PS : The shared and Android parts are currently the only ones being implemented (i don't have MacOS)

## Supported platforms

Actually Hashnode is the only supported platform thanks to
their [GraphQL API](https://api.hashnode.com/).

I'm planning to add support for other platforms like Medium (they have a write only API, so the plan
is to use RSS feeds),Dev.to, etc.

For platforms without an API neither RSS feeds, We can create a web crawler to fetch the posts.

