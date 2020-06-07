package MainLevel.Tiles;

import Tilemaps.Assets;

/**
 *
 * @author Omen
 */
public class FloorTile extends TileMainLevel {

    public FloorTile(int id) {
        super(Assets.retroFloor, id);
    }

    @Override
    public boolean isSolid() {
        return !isSolid;
    }

    @Override
    public void changeTiles() {
        this.texture = Assets.spaceFloor;
    }
}
