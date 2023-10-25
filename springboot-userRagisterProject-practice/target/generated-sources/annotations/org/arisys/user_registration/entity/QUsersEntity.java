package org.arisys.user_registration.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUsersEntity is a Querydsl query type for UsersEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsersEntity extends EntityPathBase<UsersEntity> {

    private static final long serialVersionUID = -1762718192L;

    public static final QUsersEntity usersEntity = new QUsersEntity("usersEntity");

    public final QDateEntity _super = new QDateEntity(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath gender = createString("gender");

    public final StringPath job = createString("job");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUsersEntity(String variable) {
        super(UsersEntity.class, forVariable(variable));
    }

    public QUsersEntity(Path<? extends UsersEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsersEntity(PathMetadata metadata) {
        super(UsersEntity.class, metadata);
    }

}

