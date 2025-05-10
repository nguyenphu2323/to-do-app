$(document).ready(function () {


    // Kiểm tra nút btnFilter
    $('#btnFilter').click(function (event) {
        event.preventDefault();

        // Status filter
        let statusArr = [];
        $("#statusFilter .form-check-input:checked").each(function () {
            statusArr.push($(this).val());
        });


        // Sort order
        let sortValue = $('input[name="radio-sort"]:checked').val();


        // Update URL parameters
        const currentUrl = new URL(window.location.href);
        const searchParams = currentUrl.searchParams;

        // Add or update query parameters
        searchParams.set('page', '1');
        searchParams.set('sort', sortValue);


        // Reset status parameter
        searchParams.delete('status');
        if (statusArr.length > 0) {
            searchParams.set('status', statusArr.join(','));
        }
        // Update the URL and reload the page
        window.location.href = currentUrl.toString();
    });


    // Handle auto checkbox and radio after page loading
    const params = new URLSearchParams(window.location.search);

    // Set checkboxes for 'status'
    if (params.has('status')) {
        const statusValues = params.get('status').split(',');
        statusValues.forEach(value => {
            $(`#statusFilter .form-check-input[value="${value}"]`).prop('checked', true);
        });
    }

    // Set radio button for 'sort'
    if (params.has('sort')) {
        const sortValue = params.get('sort');
        $(`input[name="radio-sort"][value="${sortValue}"]`).prop('checked', true);
    }
});