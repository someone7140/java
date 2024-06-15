db.user_accounts.createIndex( { user_setting_id: 1 }, { unique: true } );
db.user_accounts.createIndex( { gmail: 1, email: 1 }, { unique: true } );

db.post_categories.createIndex( { create_user_account_id: 1 });
db.post_categories.createIndex( { parent_category_id: 1 });

db.post_places.createIndex( { create_user_account_id: 1 });
db.post_places.createIndex( { prefecture_code: 1 });
db.post_places.createIndex( { category_id_list: 1 });
db.post_places.createIndex( { lon_lat: "2dsphere" });
