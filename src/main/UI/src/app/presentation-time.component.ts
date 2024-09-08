import { Component, OnInit } from '@angular/core';
import { TimeService } from './time.service';

@Component({
  selector: 'app-presentation-time',
  templateUrl: './presentation-time.component.html',
  styleUrls: ['./presentation-time.component.css']
})
export class PresentationTimeComponent implements OnInit {

  presentationTimes: string = '';

  constructor(private timeService: TimeService) { }

  ngOnInit(): void {
    this.timeService.getPresentationTimes().subscribe(times => {
      this.presentationTimes = times;
    });
  }
}
