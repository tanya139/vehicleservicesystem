import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { HttpService } from '../services/http';

@Component({
  selector: 'app-vehicle',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './vehicle.html'
})
export class VehicleComponent {

  vehicle = { model: '', number: '' };
  message = '';
  messageType = '';

  constructor(private http: HttpService) {}

  addVehicle() {
    this.http.post('/api/owner/vehicle', this.vehicle).subscribe({
      next: (response) => {
        this.message = 'Vehicle added successfully!';
        this.messageType = 'success';
        this.vehicle = { model: '', number: '' };
      },
      error: (err) => {
        if (err.status === 500) {
          this.message = 'Vehicle number already exists!';
        } else if (err.status === 403) {
          this.message = 'Access denied. Please login again.';
        } else {
          this.message = 'Failed to add vehicle. Try again.';
        }
        this.messageType = 'error';
      }
    });
  }
}