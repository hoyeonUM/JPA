package web.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 187729270L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final ListPath<BoardFile, QBoardFile> BoardFileList = this.<BoardFile, QBoardFile>createList("BoardFileList", BoardFile.class, QBoardFile.class, PathInits.DIRECT2);

    public final QBoardType boardType;

    public final StringPath contents = createString("contents");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> modeDate = createDateTime("modeDate", java.util.Date.class);

    public final StringPath modId = createString("modId");

    public final StringPath modIp = createString("modIp");

    public final StringPath readCount = createString("readCount");

    public final DateTimePath<java.util.Date> regDate = createDateTime("regDate", java.util.Date.class);

    public final StringPath regId = createString("regId");

    public final StringPath regIp = createString("regIp");

    public final StringPath secretYn = createString("secretYn");

    public final StringPath title = createString("title");

    public final StringPath topYn = createString("topYn");

    public final StringPath useYn = createString("useYn");

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBoard(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QBoard(PathMetadata<?> metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardType = inits.isInitialized("boardType") ? new QBoardType(forProperty("boardType")) : null;
    }

}

