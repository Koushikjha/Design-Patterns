    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.UUID;

    public class ParkingLotSystem {
        static abstract class Vehicle{
            Long vehicleNumber;
            VehicleType vehicleType;
            Vehicle(Long vehicleNumber,VehicleType vehicleType){
                this.vehicleNumber=vehicleNumber;
                this.vehicleType=vehicleType;
            }
        }
        static class Car extends Vehicle{
            Car(Long vehicleNumber){
                super(vehicleNumber,VehicleType.CAR);
            }
        }
        static class Bike extends Vehicle{
            Bike(Long vehicleNumber){
                super(vehicleNumber,VehicleType.BIKE);
            }
        }
        static class Bicycle extends Vehicle{
            Bicycle(Long vehicleNumber){
                super(vehicleNumber,VehicleType.BICYCLE);
            }
        }
        static enum VehicleType{
            CAR,BIKE,BICYCLE;
        }
        static class VehicleFactory{
            public Vehicle createVehicle(VehicleType type,Long vehicleNumber){
                return switch (type){
                    case CAR -> new Car(vehicleNumber);
                    case BIKE -> new Bike(vehicleNumber);
                    case BICYCLE -> new Bicycle(vehicleNumber);
                };
            }
        }
        static class ParkingSpot{
            Long spotId;
            VehicleType supportedType;
            Boolean isOccupied;
            ParkingSpot(Long spotId,VehicleType supportedType){
                this.spotId=spotId;
                this.supportedType=supportedType;
                isOccupied=false;
            }
        }
        static class Floor{
            Long floorId;
            List<ParkingSpot> parkingSpots;
            Floor(Long floorId,List<ParkingSpot> parkingSpots){
                this.floorId=floorId;
                this.parkingSpots=parkingSpots;
            }
        }
        static class ParkingLot{
            List<Floor> floors;
            ParkingLot(List<Floor> floors){
                this.floors=floors;
            }
        }
        static class Ticket{
            String ticketId;
            Vehicle vehicle;
            ParkingSpot parkingSpot;
            LocalDateTime entryTime;
            LocalDateTime exitTime;
            Ticket(Vehicle vehicle,ParkingSpot parkingSpot){
                this.ticketId= UUID.randomUUID().toString();
                this.vehicle=vehicle;
                entryTime=LocalDateTime.now();
                this.parkingSpot=parkingSpot;
            }
        }
        interface FeeStrategy{
            double cost(Ticket ticket);
        }
        static class BicycleStrategy implements FeeStrategy{
            @Override
            public double cost(Ticket ticket){
                return 10;
            }
        }
        static class BikeStrategy implements FeeStrategy{
            @Override
            public double cost(Ticket ticket){
                return 20;
            }
        }
        static class CarStrategy implements FeeStrategy{
            @Override
            public double cost(Ticket ticket){
                return 30;
            }
        }
        static class FeeService{
            FeeStrategy feeStrategy;
            FeeService(FeeStrategy feeStrategy){
                this.feeStrategy=feeStrategy;
            }
            public double cost(Ticket ticket){
                return feeStrategy.cost(ticket);
            }
        }
        static class ParkingService{
            ParkingLot parkingLot;
            ParkingService(ParkingLot parkingLot){
                this.parkingLot=parkingLot;
            }
            public ParkingSpot findEmptySpot(Vehicle vehicle){
                for (Floor f : parkingLot.floors){
                    for(ParkingSpot ps : f.parkingSpots){
                        if(!ps.isOccupied && ps.supportedType.equals(vehicle.vehicleType)){
                            return ps;
                        }
                    }
                }
                throw new IllegalArgumentException("No Empty Spot");
            }
            public void parkVehicle(ParkingSpot spot){
                spot.isOccupied=true;
            }
            public void removeVehicle(ParkingSpot spot){
                spot.isOccupied=false;
            }
        }
        static class EntryGate{
            ParkingService parkingService;
            EntryGate(ParkingService parkingService){
                this.parkingService=parkingService;
            }
            public Ticket entry(Vehicle vehicle){
                ParkingSpot spot=parkingService.findEmptySpot(vehicle);
                parkingService.parkVehicle(spot);
                return new Ticket(vehicle,spot);
            }
        }
        static class ExitGate{
            FeeService feeService;
            ParkingService parkingService;
            ExitGate(FeeService feeService,ParkingService parkingService){
                this.feeService=feeService;
                this.parkingService=parkingService;
            }
            public double exit(Ticket ticket){
                ticket.exitTime=LocalDateTime.now();
                parkingService.removeVehicle(ticket.parkingSpot);
                return feeService.cost(ticket);
            }
        }

    }
