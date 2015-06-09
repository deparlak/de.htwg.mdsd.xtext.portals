package de.htwg.mps.portals.model

import de.htwg.mps.portals.actor.AktorSystem
import de.htwg.mps.portals.config.TestConfiguration
import com.escalatesoft.subcut.inject.Injectable
import de.htwg.mps.portals.actor.AktorSystem

sealed trait Direction
final case object Left extends Direction
final case object Right extends Direction
final case object Up extends Direction
final case object Down extends Direction
final case object Stay extends Direction

trait Player {
  def uuid: String = null
  def position: Position
  def direction: Direction
  def paidMovementCost: Boolean = (0 == movementCost)
  def movementCost: Int
  def switchDirection(direction: Direction): Player
  def nextPosition: Position = direction match {
    case Up    => position.up
    case Down  => position.down
    case Left  => position.left
    case Right => position.right
    case Stay  => position
  }
  def validMove(movementCost: Int): Player
  def paiyMovementCost: Player
  def invalidMove: Player
  def destroy(player: Player) = false
}

abstract class Bot extends Player {
  override def toString();
  override def destroy(player: Player);
  def switchDirection(direction: Direction) = this
  def validMove(movementCost: Int);
  def invalidMove;
  def paiyMovementCost();
  protected def switchDirection(lastValid: Direction, lastInvalid: Direction): Direction = (lastValid, lastInvalid) match {
    case (Up, Up)       => Left
    case (Up, Left)     => Right
    case (Up, _)        => Down
    case (Down, Down)   => Left
    case (Down, Left)   => Right
    case (Down, _)      => Up
    case (Left, Left)   => Up
    case (Left, Up)     => Down
    case (Left, _)      => Right
    case (Right, Right) => Up
    case (Right, Up)    => Down
    case (Right, _)     => Left
    case (Stay, Up)     => Left
    case (Stay, Left)   => Right
    case (Stay, Right)  => Down
    case (Stay, Down)   => Up
    case (Stay, Stay)   => Up
  }
}