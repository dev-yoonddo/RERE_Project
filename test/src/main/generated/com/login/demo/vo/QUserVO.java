package com.login.demo.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserVO is a Querydsl query type for UserVO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserVO extends EntityPathBase<UserVO> {

    private static final long serialVersionUID = -661159456L;

    public static final QUserVO userVO = new QUserVO("userVO");

    public final StringPath email = createString("email");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public QUserVO(String variable) {
        super(UserVO.class, forVariable(variable));
    }

    public QUserVO(Path<? extends UserVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserVO(PathMetadata metadata) {
        super(UserVO.class, metadata);
    }

}

