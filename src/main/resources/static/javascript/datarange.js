// 달력
$(function () {
    $('input[name="daterange"]').daterangepicker(
        {
            locale: {
                format: "YYYY-MM-DD",
                separator: " ~ ",
                applyLabel: "확인",
                cancelLabel: "취소",
                fromLabel: "From",
                toLabel: "To",
                customRangeLabel: "Custom",
                weekLabel: "W",
                daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"],
                monthNames: [
                    "1월",
                    "2월",
                    "3월",
                    "4월",
                    "5월",
                    "6월",
                    "7월",
                    "8월",
                    "9월",
                    "10월",
                    "11월",
                    "12월",
                ],
            },
            startDate: $('input[name="startDate"]').val(),
            endDate: $('input[name="endDate"]').val(),
            drops: "auto",
        },
        function (start, end, label) {
            $('input[name="startDate"]').val(start.format("YYYY-MM-DD"));
            $('input[name="endDate"]').val(end.format("YYYY-MM-DD"));
            console.log(
                "A new date selection was made: " +
                start.format("YYYY-MM-DD") +
                " to " +
                end.format("YYYY-MM-DD")
            );
        }
    );
});