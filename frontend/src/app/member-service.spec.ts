import { TestBed } from '@angular/core/testing';

import { SendDataService } from './member-service';

describe('SendDataService', () => {
  let service: SendDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SendDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
