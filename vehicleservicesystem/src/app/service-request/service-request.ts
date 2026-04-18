import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpService } from '../services/http';

@Component({
  selector: 'app-service-request',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './service-request.html'
})
export class ServiceRequestComponent {

  vehicleId: number = 0;
  request = { description: '', status: 'REQUESTED' };
  message = '';

  constructor(private http: HttpService) {}

  submitRequest() {
    this.http.post(
      '/api/owner/service?vehicleId=' + this.vehicleId,
      this.request
    ).subscribe({
      next: () => {
        this.message = 'Service request submitted!';
        this.request = { description: '', status: 'REQUESTED' };
      },
      error: () => this.message = 'Failed to submit request'
    });
  }
}