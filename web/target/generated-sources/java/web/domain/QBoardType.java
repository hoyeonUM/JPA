package web.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBoardType is a Querydsl query type for BoardType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBoardType extends EntityPathBase<BoardType> {

    private static final long serialVersionUID = 1275911632L;

    public static final QBoardType boardType1 = new QBoardType("boardType1");

    public final StringPath boardGroup = createString("boardGroup");

    public final ListPath<Board, QBoard> boardList = this.<Board, QBoard>createList("boardList", Board.class, QBoard.class, PathInits.DIRECT2);

    public final StringPath boardOrderNum = createString("boardOrderNum");

    public final StringPath boardParentKey = createString("boardParentKey");

    public final StringPath boardType = createString("boardType");

    public final StringPath boardTypeName = createString("boardTypeName");

    public final StringPath menuKey = createString("menuKey");

    public final DateTimePath<java.util.Date> modDate = createDateTime("modDate", java.util.Date.class);

    public final StringPath modId = createString("modId");

    public final StringPath modIp = createString("modIp");

    public final DateTimePath<java.util.Date> regDate = createDateTime("regDate", java.util.Date.class);

    public final StringPath regId = createString("regId");

    public final StringPath regIp = createString("regIp");

    public final StringPath useYn = createString("useYn");

    public QBoardType(String variable) {
        super(BoardType.class, forVariable(variable));
    }

    public QBoardType(Path<? extends BoardType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardType(PathMetadata<?> metadata) {
        super(BoardType.class, metadata);
    }

}

