db.user_accounts.createIndex( { user_setting_id: 1 }, { unique: true } );
db.user_accounts.createIndex( { gmail: 1, email: 1 }, { unique: true } );
