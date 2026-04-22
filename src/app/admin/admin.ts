import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpService } from '../services/http';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.html'
})
export class AdminComponent implements OnInit {

  requests: any[] = [];
  mechanicId: number = 0;
  message = '';

  constructor(private http: HttpService) {}

  ngOnInit() {
    this.loadRequests();
  }

  loadRequests() {
    this.http.get('/api/admin/requests').subscribe({
      next: (data: any) => {
        this.requests = [...data];
      },
      error: () => console.error('Failed to load requests')
    });
  }

  assignMechanic(serviceId: number) {
    this.http.put(`/api/admin/assign/${serviceId}?mechanicId=${this.mechanicId}`, {})
    .subscribe({
      next: () => {
        this.message = 'Mechanic assigned!';
        this.loadRequests();
      },
      error: () => this.message = 'Assignment failed'
    });
  }
}