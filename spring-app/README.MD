### Key features
- Autoconfigurable repository layer (added mvn dependency -> configured database url/user/pass -> started application)
- Flyway manages database migration

### Why splitting by functionality but not layers?
Because of the better way to develop apps in the future. For example, if you want to add a new feature to your application, you should create N pull requests in separate git repos, which slows down the productivity because of the slow pipelines (our project is a perfect example of that)