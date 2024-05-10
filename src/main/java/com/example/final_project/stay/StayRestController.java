package com.example.final_project.stay;

import com.example.final_project._core.utils.ApiUtil;
import com.example.final_project.company.SessionCompany;
import com.example.final_project.room.RoomResponse;
import com.example.final_project.room.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StayRestController {
    private final StayService stayService;
    private final RoomService roomService;
    private final HttpSession session;

    // [특가 숙소리스트]
    @GetMapping("/stays/sale")
    public ResponseEntity<?> saleList() {

        List<StayResponse.SaleList> respDTO;
        respDTO = stayService.findSpecialListByRoom();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // [해외 숙소리스트]
    @GetMapping("/stays/oversea")
    public ResponseEntity<?> overseaList(){
        List<StayResponse.OverseaList> respDTO;
        respDTO = stayService.findOverseaListByCategory();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // [호텔 숙소리스트]
    @GetMapping("/stays/hotel")
    public ResponseEntity<?> hotelList(){
        List<StayResponse.HotelList> respDTO;
        respDTO = stayService.findHotelListByCategory();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // [캠핑 숙소리스트]
    @GetMapping("/stays/camping")
    public ResponseEntity<?> campingList(){
        List<StayResponse.CampingList> respDTO;
        respDTO = stayService.findCampingListByCategory();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // [모텔 숙소리스트]
    @GetMapping("/stays/motel")
    public ResponseEntity<?> motelList(){
        List<StayResponse.MotelList> respDTO;
        respDTO = stayService.findMotelListByCategory();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // [펜션 숙소리스트]
    @GetMapping("/stays/pension")
    public ResponseEntity<?> pensionList(){
        List<StayResponse.PensionList> respDTO;
        respDTO = stayService.findPentionByCategory();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));

    }

    // [홈&빌라 숙소리스트]
    @GetMapping("/stays/home-and-villa")
    public ResponseEntity<?> homeAndVilla(){
        List<StayResponse.HomeAndVillaList> respDTO;
        respDTO = stayService.findHomeAndVillaByCategory();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // [게하 숙소리스트]
    @GetMapping("/stays/guesthouse")
    public ResponseEntity<?> guesthouse(){
        List<StayResponse.GuesthouseList> respDTO;
        respDTO = stayService.findGuesthouseByCategory();

        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

    // 숙소 검색 기능 (이름, 지역, 가격, 인원 수 별 검색)
    @GetMapping("/stay/search")
    public ResponseEntity<?> searchStay(
            @RequestParam(required = false) String stayName,
            @RequestParam(required = false) String stayAddress,
            @RequestParam(required = false) Integer roomPrice,
            @RequestParam(required = false) Integer person
    ) {
        List<StayResponse.SearchListDTO> respDTO = stayService.getSearchStayList(stayName, stayAddress, roomPrice, person);
        System.out.println(respDTO);
        return ResponseEntity.ok(new ApiUtil<>(respDTO));
    }

//    @PostMapping("/api/register")
//    public ResponseEntity<?> save(@RequestBody StayRequest.SaveDTO reqDTO){
//        SessionCompany sessionCompany = (SessionCompany) session.getAttribute("sessionCompany");
//
//        StayResponse.Save respDTO = stayService.register(reqDTO,sessionCompany);
//
//        return ResponseEntity.ok(new ApiUtil<>(respDTO));
//    }

//    @PutMapping("/api/modify/{stayId}")
//    public ResponseEntity<?> update(@PathVariable Integer stayId,@RequestBody StayRequest.UpdateDTO reqDTO){
//        SessionCompany sessionCompany = (SessionCompany) session.getAttribute("sessionCompany");
//
//        stayService.update(stayId,sessionCompany,reqDTO);
//
//        return ResponseEntity.ok(new ApiUtil<>(respDTO));
//    }

//    // 숙소 화면
//    @GetMapping("/api/modify-form/{stayId}")
//    public ResponseEntity<?> updateForm(@PathVariable Integer stayId) {
//        StayResponse.UpdateForm respDTO = stayService.updateForm(stayId);
//
//        return ResponseEntity.ok(new ApiUtil<>(respDTO));
//    }

//    // 숙소 수정
//    @PutMapping("/api/modify/{stayId}")
//    public ResponseEntity<?> update(@PathVariable Integer stayId, @RequestBody StayRequest.UpdateDTO reqDTO) {
//        SessionCompany sessionCompany = (SessionCompany) session.getAttribute("sessionCompany");
//        StayResponse.Update respDTO = stayService.update(stayId, sessionCompany, reqDTO);
//
//        return ResponseEntity.ok(new ApiUtil<>(respDTO));
//    }

    // 숙소 삭제
    @PutMapping("/api/cancel/{stayId}")
    public ResponseEntity<?> delete(@PathVariable Integer stayId) {
        SessionCompany sessionCompany = (SessionCompany) session.getAttribute("sessionCompany");
        StayResponse.Delete respDTO = stayService.delete(stayId, sessionCompany);

        return ResponseEntity.ok().body(new ApiUtil<>(respDTO));
    }


    // 메인페이지
    @GetMapping("/home")
    public ResponseEntity<?> home(){
        StayResponse.AllList respDTO = stayService.findAllStayWithCategory();

        return ResponseEntity.ok().body(new ApiUtil<>(respDTO));
    }


    // 숙소를 클릭 했을 때 보여지는 숙소 상세보기
    @GetMapping("/stays/{stayId}")
    public ResponseEntity<?> stayDetail(@PathVariable Integer stayId){
        StayResponse.StayDetail respDTO = stayService.findStayDetail(stayId);

        return ResponseEntity.ok().body(new ApiUtil<>(respDTO));
    }

}
