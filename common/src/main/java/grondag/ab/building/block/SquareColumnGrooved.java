/*
 * This file is part of Ability and is licensed to the project under
 * terms that are compatible with the GNU Lesser General Public License.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership and licensing.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package grondag.ab.building.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import grondag.ab.building.block.base.FormedNonCubicPillarBlock;
import grondag.ab.building.block.init.FormedBlockMaterial;
import grondag.ab.building.block.init.FormedBlockType;
import grondag.xm.api.collision.CollisionShapes;
import grondag.xm.api.connect.world.FenceHelper;
import grondag.xm.api.modelstate.primitive.PrimitiveState;
import grondag.xm.api.primitive.simple.SquareColumn;

public class SquareColumnGrooved extends FormedNonCubicPillarBlock {
	public SquareColumnGrooved(FormedBlockType blockType) {
		super(blockType);
		FenceHelper.add(this);
	}

	@Deprecated
	@Override
	public VoxelShape getShape(BlockState blockState, BlockGetter blockView, BlockPos pos, CollisionContext entityContext) {
		return CollisionShapes.CUBE_WITH_CUTOUTS;
	}

	public static PrimitiveState createDefaultModelState(FormedBlockMaterial material) {
		final var defaultState = SquareColumn.INSTANCE.newState()
				.paint(SquareColumn.SURFACE_END, material.paint())
				.paint(SquareColumn.SURFACE_SIDE, material.paint())
				.paint(SquareColumn.SURFACE_CUT, material.paintCut())
				.paint(SquareColumn.SURFACE_INLAY, material.paintInner())
				.orientationIndex(Axis.Y.ordinal());

		SquareColumn.setCutCount(4, defaultState);
		SquareColumn.setCutsOnEdge(true, defaultState);

		return defaultState.releaseToImmutable();
	}
}
