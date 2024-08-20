package fr.edminecoreteam.corelobby.utils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public class LocationTracer extends BukkitRunnable {
    private final Entity entity;
    private final Location startPoint;
    private final Location endPoint;
    private final int steps;
    private int currentStep;

    public LocationTracer(Entity entity, Location startPoint, Location endPoint, int steps) {
        this.entity = entity;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.steps = steps;
        this.currentStep = 0;
    }

    @Override
    public void run() {
        if (currentStep >= steps) {
            // Arrived at destination, cancel the task
            this.cancel();
            return;
        }

        double progress = (double) currentStep / steps;
        double deltaX = endPoint.getX() - startPoint.getX();
        double deltaY = endPoint.getY() - startPoint.getY();
        double deltaZ = endPoint.getZ() - startPoint.getZ();

        double newX = startPoint.getX() + deltaX * progress;
        double newY = startPoint.getY() + deltaY * progress;
        double newZ = startPoint.getZ() + deltaZ * progress;

        Location newLocation = new Location(entity.getWorld(), newX, newY, newZ, entity.getLocation().getYaw(), entity.getLocation().getPitch());
        entity.teleport(newLocation);

        currentStep++;
    }
}
